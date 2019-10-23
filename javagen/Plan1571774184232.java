public class Plan1571774184232 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
DecreaseDimmer("A");
}


StartServer("A");

StartServer("C");

}

StartServer("A");

}
}
