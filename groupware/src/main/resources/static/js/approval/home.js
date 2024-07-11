document.addEventListener('DOMContentLoaded', function() {
  var submenuLinks = document.querySelectorAll('.approval_nav > ul > li > a');
  submenuLinks.forEach(function(link) {
    link.addEventListener('click', function(e) {
      var nextElement = link.nextElementSibling;
      if (nextElement && nextElement.tagName === 'UL') {
        e.preventDefault();
        if (nextElement.style.display === 'block') {
          nextElement.style.display = 'none';
          link.classList.remove('expanded');
          link.classList.add('collapsed');
        } else {
          document.querySelectorAll('.approval_nav ul ul').forEach(function(submenu) {
            submenu.style.display = 'none'; // Close other submenus
          });
          document.querySelectorAll('.approval_nav ul li a').forEach(function(otherLink) {
            otherLink.classList.remove('expanded');
            otherLink.classList.add('collapsed');
          });
          nextElement.style.display = 'block';
          link.classList.remove('collapsed');
          link.classList.add('expanded');
        }
      }
    });
  });
});

document.addEventListener('DOMContentLoaded', (event) => {
  const clickableElements = document.querySelectorAll('.clickable');
  const dropdownMenu = document.getElementById('dropdown-menu');
  const divIcon = document.getElementById('div-icon');

  clickableElements.forEach(element => {
    element.addEventListener('click', (event) => {
      event.preventDefault(); // 링크 클릭으로 인한 페이지 이동 방지
      const rect = divIcon.getBoundingClientRect();
      dropdownMenu.style.display = dropdownMenu.style.display === 'block' ? 'none' : 'block';
      dropdownMenu.style.width = rect.width + 'px';
      dropdownMenu.style.top = rect.bottom + 'px';
      dropdownMenu.style.left = rect.left + 'px';
    });
  });
});