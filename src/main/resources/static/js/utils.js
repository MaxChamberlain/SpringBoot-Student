function copy(element) {
    let textToCopy = element.innerText;
    console.log(textToCopy)
    navigator.clipboard.writeText(textToCopy);
    element.parentNode.children[1].src = '/assets/images/checkmark.png';
    element.parentNode.children[1].style.filter = ''

    setTimeout(() => {
        element.parentNode.children[1].src = '/assets/images/copy_icon.png';
        element.parentNode.children[1].style.filter = 'invert(1)'
    }, 3000)
}