public class Plan1571773180650 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
if ( StartServer("C") ) {
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

} else {
StartServer("B");
}

DecreaseTraffic("A");

if ( StartServer("A") ) {
StartServer("A");
} else {
for (int i = 0; i < 2 ; i++) {

}

}

DecreaseTraffic("A");
DecreaseTraffic("A");


DecreaseTraffic("A");
for (int i = 0; i < 4 ; i++) {
StartServer("B");
}





for (int i = 0; i < 3 ; i++) {
StartServer("C");
}


}
}
