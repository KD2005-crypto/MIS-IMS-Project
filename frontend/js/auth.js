// ================================
// CONFIG
// ================================
const API_BASE_URL = "https://mis-ims-project-1.onrender.com";

// ================================
// LOGIN
// ================================
async function loginUser(event) {
    event.preventDefault();

    const email = document.getElementById("loginEmail").value;
    const password = document.getElementById("loginPassword").value;

    try {
        const response = await fetch(`${API_BASE_URL}/api/auth/login`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ email, password })
        });

        if (!response.ok) {
            alert("Invalid email or password");
            return;
        }

        const data = await response.json();

        // Save session
        localStorage.setItem("token", data.token);
        localStorage.setItem("role", data.role);

        window.location.href = "dashboard.html";

    } catch (error) {
        console.error(error);
        alert("Backend not reachable");
    }
}

// ================================
// REGISTER
// ================================
async function registerUser(event) {
    event.preventDefault();

    const fullName = document.getElementById("registerName").value;
    const email = document.getElementById("registerEmail").value;
    const password = document.getElementById("registerPassword").value;

    try {
        const response = await fetch(`${API_BASE_URL}/api/auth/register`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                fullName,
                email,
                password,
                role: "USER"
            })
        });

        if (!response.ok) {
            alert("Registration failed");
            return;
        }

        alert("Registration successful. Please login.");
        window.location.href = "index.html";

    } catch (error) {
        console.error(error);
        alert("Backend not reachable");
    }
}

// ================================
// LOGOUT
// ================================
function logout() {
    localStorage.clear();
    window.location.href = "index.html";
}
