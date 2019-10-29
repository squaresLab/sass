public class Plan1571767776766 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

StartServer("C");
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

} else {
IncreaseTraffic("B");
}

DecreaseTraffic("A");

}



}
}
