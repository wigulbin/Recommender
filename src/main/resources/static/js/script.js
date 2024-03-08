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


function getCurrentDevices(){
    fetch('/currentDevices')
        .then(res => res.text())
        .then(html => {
            console.log(html);
            document.getElementById("currentDeviceContent").innerHTML = html;
            openDialog('currentDevices')
        })
}

function openDialog(id){
    const dialog = document.getElementById(id);
    if(dialog){
        dialog.open = true;
    }
}

function closeDialog(id){
    const dialog = document.getElementById(id);
    if(dialog){
        dialog.open = false;
    }
}

const spotifyFrames = {};
window.onSpotifyIframeApiReady = (IFrameAPI) => {
    //
    console.log(IFrameAPI);

    let element = document.getElementById('embed-iframe');
    [... document.getElementsByClassName('embed-iframe')].forEach((element, idx) => {
        let containerDiv = element.parentElement;
        containerDiv.setAttribute("aria-busy", "true")
        let options = {
            width: '100%',
            height: 352,
            uri: 'spotify:track:' + element.id
        };

        let callback = (EmbedController) => {
            containerDiv.setAttribute("aria-busy", "false")
            if(idx === 0) {
                EmbedController.play();
            }
        };
        IFrameAPI.createController(element, options, callback);
        spotifyFrames[element.id] = containerDiv;
    })
};


let currentPosition = 1;
function adjustPlayback(element, endpoint){
    if(endpoint == 'next'){
        document.documentElement.setAttribute("style", "--position: " + (++currentPosition));
    }
    if(endpoint == 'prev'){
        document.documentElement.setAttribute("style", "--position: " + (--currentPosition));
    }
}