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
    const input = document.getElementById("trackid");
    if(input){
        input.value = element.id;
        document.getElementById("frmSelectTrack").submit();
    }
}

function adjustPlayback(element, endpoint){
    fetch("/playback/" + endpoint + "?songid=" + element.id).then(res => res.text()).then(text => console.log(text));
}