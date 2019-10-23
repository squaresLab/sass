public class Plan1571769347265 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 7 ; i++) {
StartServer("A");
}

} else {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

}

if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
}

StartServer("B");
StartServer("A");


}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

}

StartServer("B");

StartServer("B");


}
}
