public class Plan1571767915071 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

StartServer("C");


for (int i = 0; i < 3 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
DecreaseTraffic("A");

StartServer("C");
DecreaseDimmer("A");


} else {
StartServer("B");
DecreaseTraffic("A");

}

}


}
}
