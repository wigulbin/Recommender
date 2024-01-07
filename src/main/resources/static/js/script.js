function search(query){
    fetch('/search?query=' + query, {method: "POST"})
        .then(res => res.text())
        .then(text => document.getElementById("searchResults").innerHTML = text)
}