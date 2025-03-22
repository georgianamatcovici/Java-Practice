HOMEWORK:
Pentru a genera nume random pentru locatii am folosit Java Faker si am creat obiectul myFaker pentru care apelez myFaker.address().fullAdddress().
Pentru a determina cele mai rapide drumuri pornind dintr-o locatie data, folosesc biblioteca JGraphT. Problema este reprezentata ca un graf orientat in care fiecare locatie este un nod, iar arcele vor avea atat timpul necesar parcurgerii drumului dintre 2 locatii, cat si probabilitatea ca drumul sa fie sigur. Astfel, am implementat clasa Route care extinde DefaultWeightedEdge. In clasa Problem am startLocation si graphMap-graful care modeleaza problema. Pentru fiecare locatie, introduc un nod in acest graf, si pt fiecare pereche de locatii (a,b) creez un obiect Route, ce are un anumit timp si o anumita probabilitate, apoi adaug un arc intre (a,b) cu obiectul Route creat. Pentru Homework, setez Weight-ul pe fiecare arc ca fiind timpul de parcurgere al acestuia.
Pentru determinarea celor mai rapide drumuri intre startLocation si celelalte locatii folosesc algoritmul lui Dijkstra din biblioteca JGraphT si afisez drumul (id-ul fiecarei locatii de pe drum) si timpul total necesar parcurgerii acestuia, începând cu Friend locations (din TreeSet), apoi continuând cu Neutral Locations si Enemy Locations(pe care le-am pus in cate un LinkedList).
BONUS:
Pentru a determina cele mai sigure rute, apelez myProblem.modifyCosts(), care va modifica Weight-ul de pe fiecare arc ca fiind -log(probabilitatea pe acel arc) in loc de timpul necesar parcurgerii acestuia (cum era la Homework). Am ales -log(probabilitate) folosind urmatorul rationament:
Avand un drum ce contine arcele e1, e2, .., er, fiecare cu o probabilitate sa fie sigur, p(ei), probabilitatea ca drumul sa fie sigur este p(e1)*p(e2)*...*p(er).
log(p(e1)*p(e2)*..*p(er))=log(p(e1))+log((p(e2))+...+log(p(er))
Deci practic, considerand costul fiecarui arc ei -> log(p(ei)), trebuie determinat drumul care are costul cel mai mare (functia log este monotona), dar aceasta este o problema de maxim, iar pentru ajunge la o problema de minim, am pus -log(p(ei))-> costul fiecarui arc ei. 
Am aplicat algoritmul Floyd-Warshall din biblioteca JGraphT pentru  determina cel mai sigur drum intre oricare 2 locatii (a, b) -in cazul in care exista. Apoi, pentru fiecare astfel de drum afisez id-urile locatiilor sale.


