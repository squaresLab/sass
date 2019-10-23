public class Plan1571774166462 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
DecreaseTraffic("A");

for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
DecreaseDimmer("B");
}

StartServer("A");

}


}
}
