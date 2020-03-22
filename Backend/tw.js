var express = require("express")
var mysql = require("mysql")
var app = express()
var bodyParser = require('body-parser')
app.use(bodyParser.json())


//database connection
var connection = mysql.createConnection({
    host     : 'localhost',
    user     : 'root',
    password : '',
    database:'tdm'
});
connection.connect();


var accountSid = 'AC9748ed28034e07b79dd5455afe0d3e8e'; // Your Account SID from www.twilio.com/console
var authToken = '642e44a81c279f47e4513fe6f6411607';   // Your Auth Token from www.twilio.com/console

var twilio = require('twilio');
var client = new twilio(accountSid, authToken);


app.post('/addpatient', function(req,res){
    var patient = JSON.parse(JSON.stringify(req.body))

    client.messages.create({
        body: 'Votre mot de passe est : '+patient.mdp,
        to: '+213'+(patient.num_tel).substring(1,10),  // Text this number
        from: '+12058130678' // From a valid Twilio number
    });

    var query = "insert into patient values (?,?,?,?,?,?,?,?)"
    connection.query(query,[patient.nss,patient.nom,patient.prenom,patient.num_tel,patient.mdp,patient.adresse,patient.initial,patient.connecte],function(error,results){
        if (error) res.send('Error');
        res.send('success');
    })

});

app.post('/addmedecin', function(req,res){
    var medecin = JSON.parse(JSON.stringify(req.body))

    client.messages.create({
        body: 'Votre mot de passe est : '+medecin.mdp,
        to: '+213'+(medecin.num_tel).substring(1,10),  // Text this number
        from: '+12058130678' // From a valid Twilio number
    });

    var query = "insert into medecin values (?,?,?,?,?,?,?,?,?,?,?,?,?)"
    connection.query(query,[medecin.id,medecin.num_tel,medecin.nom,medecin.prenom,medecin.adresse,medecin.commune,medecin.specialite,medecin.ouverture,medecin.fermeture,medecin.gps,medecin.mdp,medecin.initial,medecin.connectee],function(error,results){
        if (error) res.send('Error');
        res.send('success');
    })

});

app.post('/addrdv', function(req,res){
    var rdv = JSON.parse(JSON.stringify(req.body))

    var query = "insert into rdv values (?,?,?,?,?)"
    connection.query(query,[rdv.id,rdv.date_rdv,rdv.heure_rdv,rdv.id_med,rdv.nss_pt],function(error,results){
        if (error) res.send('Error');
        res.send('success');
    })

});

app.post('/addtrt', function(req,res){
    var traitement = JSON.parse(JSON.stringify(req.body))

    var query = "insert into traitement values (?,?,?,?)"
    connection.query(query,[traitement.id,traitement.date_fin,traitement.id_med,traitement.nss_pt],function(error,results){
        if (error) res.send('Error');
        res.send('success');
    })

});

app.post('/addinvitation', function(req,res){
    var invitation = JSON.parse(JSON.stringify(req.body))

    var query = "insert into invitation values (?,?,?,?)"
    connection.query(query,[invitation.id,invitation.nss_pt,invitation.id_med,invitation.acceptee],function(error,results){
        if (error) res.send('Error');
        res.send('success');
    })

});


app.post('/addmedicament', function(req,res){
    var medicament = JSON.parse(JSON.stringify(req.body))

    var query = "insert into medicament values (?,?)"
    connection.query(query,[medicament.id,medicament.designation],function(error,results){
        if (error) res.send('Error');
        res.send('success');
    })

});


app.get('/getmedecin/:commune/:specialite',function(req,res){ 
    var query = "select * from medecin where commune=? and specialite=?"
   connection.query(query,[req.params.commune,req.params.specialite],function(error,results){
    if (error) throw error;
    res.send(results);
})
});

app.get('/gettrtid/:nss/:id_med',function(req,res){ 
    var query = "select * from traitement where nss_pt=? and id_med=?"
   connection.query(query,[req.params.nss,req.params.id_med],function(error,results){
    if (error) throw error;
    res.send(results);
})
});

app.get('/getpatienttrt/:id_med',function(req,res){ 
    var query = "select * from patient join traitant on nss=nss_pt where id_med=?"
   connection.query(query,[req.params.id_med],function(error,results){
    if (error) throw error;
    res.send(results);
})
});

app.get('/getmedecintrt/:nss',function(req,res){ 
    var query = "SELECT * FROM medecin join traitant on id=id_med WHERE nss_pt=?"
   connection.query(query,[req.params.nss],function(error,results){
    if (error) throw error;
    res.send(results);
})
});

app.get('/getmedecinverify/:num_tel/:mdp',function(req,res){ 
    var query = "select * from medecin where num_tel=? and mdp=?"
   connection.query(query,[req.params.num_tel,req.params.mdp],function(error,results){
    if (error) throw error;
    res.send(results);
})
});

app.get('/updatemedecin/:num_tel/:mdp', function(req,res){
    var query = "update medecin set mdp = ?, initial=0 where num_tel = ?"
    connection.query(query,[req.params.mdp,req.params.num_tel],function(error,results){
        if (error) res.send('Error');
        res.send('success');
    })

});

app.get('/getpatientverify/:num_tel/:mdp',function(req,res){ 
    var query = "select * from patient where num_tel=? and mdp=?"
   connection.query(query,[req.params.num_tel,req.params.mdp],function(error,results){
    if (error) throw error;
    res.send(results);
})
});

app.get('/updatepatient/:num_tel/:mdp', function(req,res){
    var query = "update patient set mdp = ?, initial=0 where num_tel = ?"
    connection.query(query,[req.params.mdp,req.params.num_tel],function(error,results){
        if (error) res.send('Error');
        res.send('success');
    })

});

app.get('/getinvitation/:nss/:id_med',function(req,res){ 
    var query = "select * from invitation where nss_pt=? and id_med=?"
   connection.query(query,[req.params.nss,req.params.id_med],function(error,results){
    if (error) throw error;
    res.send(results);
})
});


var server = app.listen(8082,function(){
    var host = server.address().address
    var port = server.address().port
});