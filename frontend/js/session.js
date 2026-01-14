// session.js

function setSession(user) {
    sessionStorage.setItem("user", JSON.stringify(user));
}

function getSession() {
    return JSON.parse(sessionStorage.getItem("user"));
}

function checkSession() {
    const user = getSession();
    if (!user) {
        alert("Session expired. Please login again.");
        window.location.href = "login.html";
    }
}

function logout() {
    sessionStorage.clear();
    window.location.href = "login.html";
}
