document.querySelector('.side nav').addEventListener('click', (e) => {
	if (e.target.classList.contains('menu-title')) {
		const submenu = e.target.nextElementSibling;
		if (submenu && submenu.classList.contains('submenu')) {
			submenu.classList.toggle('show');
		}
	}
});