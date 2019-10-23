public class Plan1571774199034 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
DecreaseDimmer("A");
}

for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
StartServer("B");
StartServer("A");

}

StartServer("A");

}


}
}
