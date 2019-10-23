public class Plan1571775300372 extends Plan { 
public static void main(String[] args) { 
if ( StartServer("B") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
StartServer("A");
}

StartServer("C");

for (int i = 0; i < 3 ; i++) {
StartServer("A");
if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {

}

StartServer("C");


}


}
}
