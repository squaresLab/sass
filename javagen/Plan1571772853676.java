public class Plan1571772853676 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseDimmer("C") ) {

IncreaseDimmer("B");

} else {
StartServer("B");
for (int i = 0; i < 5 ; i++) {
StartServer("A");
}


}

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
StartServer("A");

}

DecreaseTraffic("A");

}


}
}
