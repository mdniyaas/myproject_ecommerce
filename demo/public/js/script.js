/* MyShop — Global UI Enhancements */

(function () {
    'use strict';

    // Navbar scroll effect
    const navbar = document.getElementById('mainNavbar');
    if (navbar) {
        const onScroll = () => {
            if (window.scrollY > 40) {
                navbar.classList.add('scrolled');
            } else {
                navbar.classList.remove('scrolled');
            }
        };
        window.addEventListener('scroll', onScroll, { passive: true });
        onScroll();
    }

    // Fade-in on scroll for cards
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -40px 0px'
    };

    const fadeObserver = new IntersectionObserver((entries) => {
        entries.forEach((entry) => {
            if (entry.isIntersecting) {
                entry.target.classList.add('fade-in');
                fadeObserver.unobserve(entry.target);
            }
        });
    }, observerOptions);

    document.querySelectorAll('.product-card, .category-card, .feature-box, .dashboard-card').forEach((el) => {
        el.style.opacity = '0';
        fadeObserver.observe(el);
    });

    // Legacy cart button support
    const cartButtons = document.querySelectorAll('.add-cart');
    if (cartButtons.length) {
        let cart = JSON.parse(localStorage.getItem('cart') || '[]');
        cartButtons.forEach((button) => {
            button.addEventListener('click', () => {
                const name = button.getAttribute('data-name');
                const price = button.getAttribute('data-price');
                cart.push({ name, price });
                localStorage.setItem('cart', JSON.stringify(cart));
                alert(name + ' added to cart');
            });
        });
    }
})();
