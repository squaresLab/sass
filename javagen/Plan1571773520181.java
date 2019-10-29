public class Plan1571773520181 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");

}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
if ( StartServer("C") ) {
StartServer("B");
IncreaseTraffic("B");

DecreaseTraffic("A");

} else {
StartServer("B");
StartServer("A");

}


}


StartServer("A");
StartServer("C");


}
}
