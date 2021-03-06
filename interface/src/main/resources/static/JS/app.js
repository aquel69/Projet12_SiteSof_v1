// Navigation bar color change

let nav = document.getElementById('barre_menu');
let liens_icon = document.getElementsByClassName('icone');
let liens = document.getElementsByClassName('liensNav');
let lien_deconnection = document.getElementsByClassName('lien_deconnect');
let lien_connection = document.getElementsByClassName('lien_se_connecter');

// onload pour commencer et onresize pour dynamique
window.onresize = colorHeader;
window.onload = colorHeader;

function colorHeader() {
    let myWidth = window.innerWidth;

    // myWidth dynamique

    window.onscroll = function () {

        if (myWidth > 768) {
            if ( document.documentElement.scrollTop > 2950 ) {
                nav.style.background = "#e8bb8c";
                liens[0].style.color = "#373737";
                liens[1].style.color = "#373737";
                liens[2].style.color = "#373737";
                liens[3].style.color = "#373737";
                liens[4].style.color = "#373737";
                if (liens[5] != null) {
                    liens[5].style.color = "#373737";
                }
                if (liens[6] != null) {
                    liens[6].style.color = "#373737";
                }

                liens_icon[0].style.color = "#373737";
                liens_icon[1].style.color = "#373737";
                liens_icon[2].style.color = "#373737";

                if (lien_deconnection[0] != null) {
                    lien_deconnection[0].style.color = "#373737";
                }
                if (lien_deconnection[1] != null) {
                    lien_deconnection[1].style.color = "#373737";
                }
                if (lien_deconnection[2] != null) {
                    lien_deconnection[2].style.color = "#373737";
                }
                if (lien_deconnection[3] != null) {
                    lien_deconnection[3].style.color = "#373737";
                }
                if (lien_connection[0] != null) {
                    lien_connection[0].style.color = "#373737";
                }
            } else {

                nav.style.background = "#8A2BE2FF";


                liens[0].style.color = "#f7f7f7";
                liens[1].style.color = "#f7f7f7";
                liens[2].style.color = "#f7f7f7";
                liens[3].style.color = "#f7f7f7";
                liens[4].style.color = "#f7f7f7";
                if (liens[5] != null) {
                    liens[5].style.color = "#f7f7f7";
                }
                if (liens[6] != null) {
                    liens[6].style.color = "#f7f7f7";
                }

                liens_icon[0].style.color = "#f7f7f7";
                liens_icon[1].style.color = "#f7f7f7";
                liens_icon[2].style.color = "#f7f7f7";


                if (lien_deconnection[0] != null) {
                    lien_deconnection[0].style.color = "#f7f7f7";
                }
                if (lien_deconnection[1] != null) {
                    lien_deconnection[1].style.color = "#f7f7f7";
                }
                if (lien_deconnection[2] != null) {
                    lien_deconnection[2].style.color = "#f7f7f7";
                }
                if (lien_deconnection[3] != null) {
                    lien_deconnection[3].style.color = "#f7f7f7";
                }
                if (lien_connection[0] != null) {
                    lien_connection[0].style.color = "#f7f7f7";
                }
            }

            // second else pour mettre la nav en clair et bloqu?? l'anim du dessus

        } else {
            nav.style.background = "#8A2BE2FF";
            liens[0].style.color = "#373737";
            liens[1].style.color = "#373737";
            liens[2].style.color = "#373737";
            liens[3].style.color = "#373737";
            liens[4].style.color = "#373737";
            if (liens[5] != null) {
                liens[5].style.color = "#373737";
            }
            if (liens[6] != null) {
                liens[6].style.color = "#373737";
            }


            liens_icon[0].style.color = "#373737";
            liens_icon[1].style.color = "#373737";
            liens_icon[2].style.color = "#373737";

            if (lien_deconnection[0] != null) {
                lien_deconnection[0].style.color = "#373737";
            }
            if (lien_deconnection[1] != null) {
                lien_deconnection[1].style.color = "#373737";
            }
            if (lien_deconnection[2] != null) {
                lien_deconnection[2].style.color = "#373737";
            }
            if (lien_deconnection[3] != null) {
                lien_deconnection[3].style.color = "#373737";
            }
            if (lien_connection[0] != null) {
                lien_connection[0].style.color = "#373737";
            }
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

// Navigation Jquery, nav et arrow

$("#arrow").click(function () {
    $('html,body').animate({
            scrollTop: $("#bio").offset().top - 50 // moins 50 pour que la pointe de clip path ne rentre pas dans les titres
        },
        'slow');
});


$(".liensNav:nth-child(1)").click(function () {
    $('html,body').animate({
            scrollTop: $("#accueil").offset().top
        },
        'slow');
});

$(".liensNav:nth-child(2)").click(function () {
    $('html,body').animate({
            scrollTop: $("#bio").offset().top - 50
        },
        'slow');
});


$(".liensNav:nth-child(3)").click(function () {
    $('html,body').animate({
            scrollTop: $("#concerts").offset().top - 50
        },
        'slow');
});

$(".liensNav:nth-child(4)").click(function () {
    $('html,body').animate({
            scrollTop: $("#album").offset().top - 50
            // offset prends les coordonn??s de contact et les retournent en top et left et ensuite on y va en cliquant
        },
        'slow');
});

$(".liensNav:nth-child(5)").click(function () {
    $('html,body').animate({
            scrollTop: $("#contact").offset().top
            // offset prends les coordonn??s de contact et les retournent en top et left et ensuite on y va en cliquant
        },
        'slow');
});




