function search(query){
    fetch('/search?query=' + query, {method: "POST"})
        .then(res => res.text())
        .then(text => {
            document.getElementById("searchResults").innerHTML = text;
            [...document.getElementsByClassName("selectableRow")].forEach(element => element.onclick=selectableOnClick)
        })
}
function searchNext(){
    fetch('/search/next')
        .then(res => res.text())
        .then(text => {
            document.getElementById("searchResults").innerHTML = text;
            [...document.getElementsByClassName("selectableRow")].forEach(element => element.onclick=selectableOnClick)
        })
}
function searchPrev(){
    fetch('/search/prev')
        .then(res => res.text())
        .then(text => {
            document.getElementById("searchResults").innerHTML = text;
            [...document.getElementsByClassName("selectableRow")].forEach(element => element.onclick=selectableOnClick)
        })
}

window.onload = () => {
    [...document.getElementsByClassName("selectableRow")].forEach(element => element.onclick=selectableOnClick)
}

function selectableOnClick(e){
    const element = e.currentTarget;
    alert(element.id);
}