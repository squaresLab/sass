public class Plan1571768093001 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
DecreaseTraffic("A");
StartServer("A");


for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
StartServer("C");
StartServer("A");


}

DecreaseTraffic("A");

}


}
}
