public class Plan1571773784555 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("C") ) {
StartServer("B");
DecreaseTraffic("A");

for (int i = 0; i < 2 ; i++) {
StartServer("A");
}


} else {
StartServer("B");
}

}

}
}
