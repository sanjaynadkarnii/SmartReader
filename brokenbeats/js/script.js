// Ensure script runs after the page has fully loaded
document.addEventListener("DOMContentLoaded", () => {
    const darkModeToggle = document.getElementById("dark-mode-toggle");
    const body = document.body;

    if (!darkModeToggle) {
        console.error("Dark mode toggle button not found!");
        return;
    }

    // Check if Dark Mode preference is saved in localStorage
    if (localStorage.getItem("dark-mode") === "enabled") {
        body.classList.add("dark-mode");
        darkModeToggle.textContent = "☀️ Light Mode";
    }

    // Toggle Dark Mode
    darkModeToggle.addEventListener("click", () => {
        body.classList.toggle("dark-mode");

        if (body.classList.contains("dark-mode")) {
            localStorage.setItem("dark-mode", "enabled");
            darkModeToggle.textContent = "☀️ Light Mode";
        } else {
            localStorage.setItem("dark-mode", "disabled");
            darkModeToggle.textContent = "🌙 Dark Mode";
        }
    });

    // Smooth Page Load Transition
    document.body.style.opacity = "1";
});
