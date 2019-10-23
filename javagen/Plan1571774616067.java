public class Plan1571774616067 extends Plan { 
public static void main(String[] args) { 
DecreaseTraffic("A");
if ( StartServer("A") ) {
if ( DecreaseTraffic("A") ) {
DecreaseTraffic("A");
} else {

}

} else {
DecreaseDimmer("B");
}

for (int i = 0; i < 7 ; i++) {
StartServer("B");
}

StartServer("C");

StartServer("C");


if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

} else {
StartServer("C");
}



}
}
