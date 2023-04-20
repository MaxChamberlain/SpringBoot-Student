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

function onHeaderLoad(){
    const linkElement = document.getElementById('my-account-link')
    linkElement.href = '/account/' + getCookie('ACCESS_TOKEN')
}

function getCookie(name) {
    let cookieArr = document.cookie.split(";");

    for(let i = 0; i < cookieArr.length; i++) {
        let cookiePair = cookieArr[i].split("=");

        if(name == cookiePair[0].trim()) {
            return cookiePair[1].replace('Bearer ', '')
        }
    }

    return null;
}

function navigateToAccountPage(){
    window.location.href = '/account/' + getCookie('ACCESS_TOKEN')
}