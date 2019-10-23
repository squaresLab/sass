public class Plan1571774541743 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

} else {
IncreaseDimmer("B");
}

StartServer("B");

StartServer("C");


}

}
}
