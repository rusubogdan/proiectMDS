proiectMDS
==========
Aplicatia server
  Aceasta aplicatie foloseste o baza de date unde salveaza utiliatorii si legaturile de prietenie dintre acestia. Baza de 
date folosita este o baza de date Oracle. Legatura dintre clasele si obiecte aplicatie in Java , cat si restrictiile lor
sunt transferate in baza de date folosind framework-ul Hibernate. Maparile sunt facute prin adnotari iar configuratia
pentru Hibernate este facuta cu ajutorul fisierelor XML.
  Conexiunea cu clientii este realizata prin socket-uri. Fiecare aplicatie client are adresa serverului. Serverul ofera
utilizatorilor posibilitatea de a se inregistra, de a se loga, de a stabili relatii de prietenie, de a cere informatii 
personale despre un prieten sau de a actualiza informatiile sale si, de asemenea, de a comunica prin mesaje text.
  Comunicarea server-clinti se face cu ajutorul unor obiecte de tip Message care sunt tinute separat de cele doua aplicatii
intr-un proiect numit MessageAPI (application programming interface) si care face parte din build-path-ul celor doua
proiecte. Fiecare mesaj implementeaza interfata Message, astfel prin serializare nu se stie ce tip de obiect s-a primit
pe o anumita conexiune pana nu ii este verificata clasa obiectului. Orice tip de mesaj trece prin server si este analizat 
de acesta si, eventual, trimis mai departe in aceeasi forma (chatMessage). 
  Pentru cazurile de insucces la logare, inregistrare sau adaugare prieteni, serverul trimite un mesaj corespunzator catre
respectivul utilizator. Pentru fiecare utilizator se deschide un thread care va asculta mesajele primite (Connection)  si 
un alt thread pentru a trimite mesajele catre respectiva conexiune(MessageSender). De asemenea serverul vede clientii online 


Aplicatia client
  Un utilizator folosind aceasta aplicatie se poate conecta la server daca acesta este pornit. Acesta poate sa se 
inregistreze, sa se logheze si sa comunice cu alti utilizatori pe care ii adauga dupa logare. Utilizatorul dispune de 4 
interfete grafice : fereastra de signIn si signUp (AppMainWindow), fereastra cu prietenii online (ListOfUsersWindow), 
fereastra prin care comunica cu prietenii (UserChatWindow) si fereastra pentru datele personale ale prietenilor sau 
actualizare de date personale proprii (FriendInfoWindow si UpadateInfoWindow). La signOut prietenii acestuia sunt informati
de faptul ca un utilizator a iesit prin faptul ca dispare din lista de prieteni online.
