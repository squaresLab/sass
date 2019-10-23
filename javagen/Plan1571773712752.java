public class Plan1571773712752 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("B");
}

DecreaseTraffic("A");

StartServer("B");

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

}

if ( StartServer("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 2 ; i++) {

}

}

StartServer("C");


}

} else {
DecreaseDimmer("C");
}

}
}
