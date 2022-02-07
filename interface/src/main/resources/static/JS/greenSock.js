/* Effet Js */

TweenMax.to("#arrow", 2, {
    delay: 2.7,
    opacity: 1,
    ease: Expo.easeInOut
})

TweenMax.to("#accueil h1", 2, {
    opacity: 1,
    ease: Expo.easeInOut
})

TweenMax.to("#accueil h2", 3, {
    opacity: 1,
    ease: Expo.easeInOut
})

TweenMax.to("#navigationBarre", 1, {
    opacity: 1,
    ease: Expo.easeInOut
})

TweenMax.from("#navigationBarre", 1.5, {
    delay: .2,
    y: -40,
    ease: Expo.easeIn
})

TweenMax.to("#lien_icone", 1, {
    opacity: 1,
    ease: Expo.easeInOut
})

TweenMax.from("#lien_icone", 1.5, {
    delay: .2,
    y: -40,
    ease: Expo.easeIn
})

TweenMax.to("#lien_deconnection", 1, {
    opacity: 1,
    ease: Expo.easeInOut
})

TweenMax.from("#lien_deconnection", 1.5, {
    delay: .2,
    y: -40,
    ease: Expo.easeIn
})


/* navbar qui apparait 1 par 1 */

TweenMax.from("#navigationBarre a:nth-child(1)", 2.5, {
    delay: 1.2,
    opacity: 0,
    y: 20,
    ease: Expo.easeInOut
})
TweenMax.from("#navigationBarre a:nth-child(2)", 3, {
    delay: 1.2,
    opacity: 0,
    y: 20,
    ease: Expo.easeInOut
})
TweenMax.from("#navigationBarre a:nth-child(3)", 3.5, {
    delay: 1.2,
    opacity: 0,
    y: 20,
    ease: Expo.easeInOut
})
TweenMax.from("#navigationBarre a:nth-child(4)", 4, {
    delay: 1.2,
    opacity: 0,
    y: 20,
    ease: Expo.easeInOut
})
TweenMax.from("#navigationBarre a:nth-child(5)", 4.5, {
    delay: 1.2,
    opacity: 0,
    y: 20,
    ease: Expo.easeInOut
})
TweenMax.from("#navigationBarre a:nth-child(6)", 5, {
    delay: 1.2,
    opacity: 0,
    y: 20,
    ease: Expo.easeInOut
})

TweenMax.from("#lien_icone a:nth-child(1)", 5.5, {
    delay: 1.2,
    opacity: 0,
    y: 20,
    ease: Expo.easeInOut
})


TweenMax.from("#lien_icone a:nth-child(2)", 6, {
    delay: 1.2,
    opacity: 0,
    y: 20,
    ease: Expo.easeInOut
})

TweenMax.from("#lien_icone a:nth-child(3)", 6.5, {
    delay: 1.2,
    opacity: 0,
    y: 20,
    ease: Expo.easeInOut
})


TweenMax.from("#lien_deconnection a:nth-child(1)", 7.0, {
    delay: 1.2,
    opacity: 0,
    y: 20,
    ease: Expo.easeInOut
})