const API_URL = '/api';
let token = localStorage.getItem('token');
let isLoginMode = true;

// DOM Elements
const views = { auth: document.getElementById('auth-view'), dashboard: document.getElementById('dashboard-view') };
const authForm = document.getElementById('auth-form');
const authBtn = document.getElementById('auth-btn');
const toggleRegister = document.getElementById('toggle-register');
const authError = document.getElementById('auth-error');
const userDisplay = document.getElementById('user-display');
const projectsGrid = document.getElementById('projects-grid');
const projectModal = document.getElementById('project-modal');

// Init
if (token) showDashboard();
else showAuth();

// Event Listeners
toggleRegister.addEventListener('click', () => {
    isLoginMode = !isLoginMode;
    authBtn.textContent = isLoginMode ? 'Log In' : 'Register';
    document.querySelector('.toggle-auth').innerHTML = isLoginMode ? 
        'Need an account? <span id="toggle-register">Register</span>' : 
        'Already have an account? <span id="toggle-register">Log In</span>';
    document.getElementById('toggle-register').addEventListener('click', () => toggleRegister.click());
    authError.textContent = '';
});

authForm.addEventListener('submit', async (e) => {
    e.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const endpoint = isLoginMode ? '/auth/login' : '/auth/register';
    
    try {
        const res = await fetch(`${API_URL}${endpoint}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(isLoginMode ? { username, password } : { username, password, email: username+'@example.com' })
        });
        const data = await res.json();
        if (res.ok) {
            token = data.token;
            localStorage.setItem('token', token);
            localStorage.setItem('username', username);
            showDashboard();
        } else {
            // Handle Spring validation errors (e.g., password < 6 chars)
            if (data.password) authError.textContent = data.password;
            else if (data.username) authError.textContent = data.username;
            else authError.textContent = data.message || 'Authentication failed. Please check your credentials.';
        }
    } catch (err) { authError.textContent = 'Server error. Is the backend running?'; }
});

document.getElementById('logout-btn').addEventListener('click', () => {
    localStorage.removeItem('token');
    token = null;
    showAuth();
});

document.getElementById('new-project-btn').addEventListener('click', () => projectModal.classList.add('active'));
document.getElementById('cancel-project-btn').addEventListener('click', () => projectModal.classList.remove('active'));

document.getElementById('project-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const payload = {
        name: document.getElementById('proj-name').value,
        githubRepository: document.getElementById('proj-repo').value,
        cloudProvider: document.getElementById('proj-cloud').value,
        environment: document.getElementById('proj-env').value,
        description: 'NebulaOps Managed Project'
    };
    
    const res = await fetch(`${API_URL}/projects`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${token}` },
        body: JSON.stringify(payload)
    });
    if (res.ok) {
        projectModal.classList.remove('active');
        document.getElementById('project-form').reset();
        loadProjects();
    }
});

// Functions
function showAuth() { views.auth.classList.add('active'); views.dashboard.classList.remove('active'); }
function showDashboard() { 
    views.auth.classList.remove('active'); views.dashboard.classList.add('active'); 
    userDisplay.textContent = 'Logged in as ' + localStorage.getItem('username');
    loadProjects();
}

async function loadProjects() {
    const res = await fetch(`${API_URL}/projects`, { headers: { 'Authorization': `Bearer ${token}` } });
    if (res.status === 401 || res.status === 403) {
        localStorage.removeItem('token'); token = null; return showAuth();
    }
    const projects = await res.json();
    
    projectsGrid.innerHTML = '';
    if (projects.length === 0) {
        projectsGrid.innerHTML = '<div style="color:var(--text-muted); padding:20px;">No projects found. Create one to get started!</div>';
        return;
    }

    projects.forEach(p => {
        const card = document.createElement('div');
        card.className = 'project-card glass';
        card.innerHTML = `
            <h3>${p.name}</h3>
            <p>GitHub: ${p.githubRepository}</p>
            <div class="meta">
                <span class="badge">${p.cloudProvider}</span>
                <span class="badge">${p.environment}</span>
            </div>
            <button class="btn-outline deploy-btn" data-id="${p.id}">Trigger Deployment</button>
            <div class="deployments-list" id="deps-${p.id}"></div>
        `;
        projectsGrid.appendChild(card);
        loadDeployments(p.id);
    });

    document.querySelectorAll('.deploy-btn').forEach(btn => {
        btn.addEventListener('click', async (e) => {
            const id = e.target.getAttribute('data-id');
            e.target.disabled = true;
            e.target.textContent = 'Deploying...';
            await fetch(`${API_URL}/deployments`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json', 'Authorization': `Bearer ${token}` },
                body: JSON.stringify({ projectId: id })
            });
            setTimeout(() => { e.target.disabled = false; e.target.textContent = 'Trigger Deployment'; loadDeployments(id); }, 1500);
            loadDeployments(id); 
        });
    });
}

async function loadDeployments(projectId) {
    const res = await fetch(`${API_URL}/deployments/project/${projectId}`, { headers: { 'Authorization': `Bearer ${token}` } });
    if (!res.ok) return;
    const deps = await res.json();
    const container = document.getElementById(`deps-${projectId}`);
    if(!container) return;
    
    container.innerHTML = deps.length === 0 ? '<div style="font-size:12px;color:var(--text-muted)">No deployment history</div>' : '';
    
    // Sort descending by id to show latest first, take top 4
    deps.sort((a,b) => b.id - a.id).slice(0, 4).forEach(d => {
        container.innerHTML += `
            <div class="deployment-item">
                <span>Deploy #${d.id}</span>
                <span class="status-${d.status}">${d.status}</span>
            </div>
        `;
        // if any is pending, poll again in 2 seconds
        if (d.status === 'PENDING') {
            setTimeout(() => loadDeployments(projectId), 2500);
        }
    });
}
