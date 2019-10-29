public class Plan1571774280606 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
StartServer("C");
for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}


DecreaseTraffic("A");
for (int i = 0; i < 2 ; i++) {
if ( StartServer("B") ) {
StartServer("C");
} else {
IncreaseTraffic("C");
}

}



}

}
}
