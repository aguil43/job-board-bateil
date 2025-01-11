let currentIndex = 0;
const images = document.querySelectorAll('.carousel-image');
const totalImages = images.length;

function moveSlide(step) {
    currentIndex += step;
    if (currentIndex < 0) {
        currentIndex = totalImages - 1;
    } else if (currentIndex >= totalImages) {
        currentIndex = 0;
    }

    const newTransform = -currentIndex * 600;
    document.querySelector('.images').style.transform = `translateX(${newTransform}px)`;
}
