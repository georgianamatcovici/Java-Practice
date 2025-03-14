HOMEWORK:
Am implementat clasa Airport care contine o lista de runways si clasa Flight.
Clasa Flight contine:
-un assignedAircraft de tip Aircraft, care reprezinta aircraft-ul care efectuaza zborul respectiv
-un idNUmber
-landingInterval de tip TimeInterval
Clasa TimeInterval este derivata din Pair<LocalTime, LocalTime>.

Clasa Schedule descrie problema si contine un Airport (care are lista de Runways), flightsSet care este multimea zborurilor (de tip Set<Flight>), un map flightMap care va fi folosit pentru a rezolva cerinta - pt a asigna fiecarui Flight un Runway. (graful myGraph va fi folost la bonus)

In constructorul clasei Schedule, instantiez multimea zborurilor cu un TreeSet, iar ulterior, cand voi adauga zboruri, acestea vor fi introduse sortate in ordine crescatoare dupa timpul de finalizare (similar cu problema Interval Scheduling), cu ajutorul comparatorului SortComparator care implementeaza Comparator<Flight> si suprascrie metoda compare().

Metoda solveSchedule() rezolva problema parcurgand multimea zborurilor sortate in ordine crescatoare dupa timpii de finalizare. La fiecare pas, pentru zborul curent, este parcursa lista de Runways si pt fiecare Runway se verifica in Map daca are alte zboruri asignate care ar putea intra in conflict cu zborul curent. Daca nu, zborul curent este asignat la runway, altfel se continua cautarea unei runway libere.
Metoda printSolution() parcurge Map-ul si afiseaza solutia.

BONUS:
Am creat clasa Graph care va reprezenta sub forma unei matrici de adiacenta (adjancencyMatrix) un graf in care fiecarui nod ii corespunde un Flight (vom avea flightsNumber noduri), iar intre 2 noduri exista muchie daca aceste 2 Flights intra in conflict. flightsRunways[] este array-ul in care va fi stocata "colorarea" nodurilor dupa apelul metodei greedyColoring(). Variabila availableRunways retine numarul de piste disponibile in aeroport, pt a verifica daca pt graful dat exista o colorare care foloseste availableRunways culori, sau avem nevoie de mai multe culori (runways)-retinem in variabila enoughRunways. chiOfG-numarul de colorare al grafului.
In clasa Schedule, in solveSchedule() este creat un graf, iar pe masura ce se verifica intre care zboruri avem conflict (se suprapun), sunt setate in graf muchiile corespunzatoare.
In myGraph, metoda printColoring() apeleaza greedyColoring(), care este implementarea algoritmului de colorare Greedy din cursul 11 de la Algoritmica Grafurilor. Dupa apelul acestei metode, in fligtsRunways[i] voi avea culoarea cu care a fost colorat i (pista asociata zborului i), pentru fiecare i, iar Map<Integer, List<Integer>> coloringClasses este folosit pentru a retine pentru fiecare culoare (pista) lista nodurilor colorate cu acea culoare (zborurilor asignate pt pista resoectiva).
Daca am nevoie de mai multe runways (culori), decat cele existente, este afisat un mesaj, sugerând o reprogramare pt anumite zboruri, iar daca avem destule runways, se apeleaza adjustClasses(). Metoda adjustClasses() parcurge clasele de colorare, si in caz ca diferenta dintre cardinalul unor clase este mai mare decat 1 in modul, muta nodurile dintr-o clasa in alta, verificând ca in urma mutarii sa nu apara conflicte.


 