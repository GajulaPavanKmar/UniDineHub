function changeSlide(slideIndex) {
    const slides = document.querySelector('.slides');
    slides.style.transform = `translateX(-${slideIndex * 100}%)`;
}
function changeContent(direction) {
    var contents = document.getElementsByClassName('carousel-content');
    var currentIndex;

    // Find the currently displayed content
    for (var i = 0; i < contents.length; i++) {
        if (contents[i].style.display !== 'none') {
            currentIndex = i;
            break;
        }
    }

    var nextIndex = currentIndex + direction;
    if (nextIndex >= contents.length) {
        nextIndex = 0;
    } else if (nextIndex < 0) {
        nextIndex = contents.length - 1;
    }

    contents[currentIndex].style.left = direction > 0 ? '-100%' : '100%';
    contents[nextIndex].style.display = 'block';
    contents[nextIndex].style.left = '0';
}

var currentIndex = 1;
showContent(currentIndex);

function changeContent(n) {
    showContent(currentIndex += n);
}

function showContent(n) {
    var i;
    var contents = document.getElementsByClassName("carousel-content");
    if (n > contents.length) {currentIndex = 1}    
    if (n < 1) {currentIndex = contents.length}
    for (i = 0; i < contents.length; i++) {
        contents[i].style.display = "none";  
    }
    contents[currentIndex - 1].style.display = "block";  
}


