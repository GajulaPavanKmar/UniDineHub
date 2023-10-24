document.addEventListener("DOMContentLoaded", function() {
    const menuButtons = document.querySelectorAll('.menu-btn');

    menuButtons.forEach(button => {
        button.addEventListener('click', function() {
            const selectedMenu = this.getAttribute('data-menu');

            // Hide all menu sections
            document.querySelectorAll('.menu-section').forEach(section => {
                section.style.display = 'none';
            });

            // Display the selected menu section
            document.getElementById(selectedMenu).style.display = 'block';
        });
    });
});
