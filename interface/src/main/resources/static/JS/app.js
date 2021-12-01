// Navigation bar color change

let nav = document.getElementById('navigation');
let liens_icon = document.getElementsByClassName('lien_nav_icone');
let liens = document.getElementsByClassName('liensNav');
let icon = document.getElementById('icon');


// onload pour commencer et onresize pour dynamique
window.onresize = colorHeader;
window.onload = colorHeader;

function colorHeader() {
    let myWidth = window.innerWidth;

    // myWidth dynamique

    window.onscroll = function () {

        if (myWidth > 768) {


            if (document.documentElement.scrollTop > 2950) {

                nav.style.background = "#e8bb8c";
                liens[0].style.color = "#373737";
                liens[1].style.color = "#373737";
                liens[2].style.color = "#373737";
                liens[3].style.color = "#373737";
                liens[4].style.color = "#373737";

                liens_icon[0].style.color = "#373737";
                liens_icon[1].style.color = "#373737";
                liens_icon[2].style.color = "#373737";

            } else {
                nav.style.background = "#8A2BE2FF";
                liens[0].style.color = "#f7f7f7";
                liens[1].style.color = "#f7f7f7";
                liens[2].style.color = "#f7f7f7";
                liens[3].style.color = "#f7f7f7";
                liens[4].style.color = "#f7f7f7";

                liens_icon[0].style.color = "#f7f7f7";
                liens_icon[1].style.color = "#f7f7f7";
                liens_icon[2].style.color = "#f7f7f7";
            }

            // second else pour mettre la nav en clair et bloqué l'anim du dessus

        } else {
            nav.style.background = "#8A2BE2FF";
            liens[0].style.color = "#373737";
            liens[1].style.color = "#373737";
            liens[2].style.color = "#373737";
            liens[3].style.color = "#373737";
            liens[4].style.color = "#373737";

            liens_icon[0].style.color = "#373737";
            liens_icon[1].style.color = "#373737";
            liens_icon[2].style.color = "#373737";
        }
    }




};



// Responsiv nav

function toggleNav() {

    if (nav.className === "") {
        nav.className += "responsive";

    } else {
        nav.className = "";

    }
}



$('html').click(function () {
    if (nav.className += "responsive") {

        nav.className = "";
    }
});
$('#icon').click(function (event) {
    event.stopPropagation(); // stop la propagation, donc l'animation audessus
});