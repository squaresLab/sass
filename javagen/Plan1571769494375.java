public class Plan1571769494375 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
DecreaseTraffic("A");

}

for (int i = 0; i < 2 ; i++) {
if ( StartServer("C") ) {
DecreaseTraffic("A");
StartServer("B");
StartServer("B");


} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}

StartServer("C");

}



}
}
