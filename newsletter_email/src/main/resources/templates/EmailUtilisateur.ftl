<!DOCTYPE HTML>
    <head>
        <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
        <#--<#include "/src/main/resources/static/styleEmail.css">-->
    </head>
    <body>
        <table>
            <tr>
                <td>
                    <p>
                        <pre>${mail.message}</pre>
                    </p>
                </td>
            </tr>
            <tr>
                <td>
                    <p>Le message provient de l'adresse email suivante : ${mail.expediteur}</p>
                </td>
            </tr>
        </table>
    </body>
</html>