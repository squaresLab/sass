public class Plan1571767968194 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

StartServer("A");

} else {
StartServer("C");
}

DecreaseTraffic("A");

}


}
}
