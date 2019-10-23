public class Plan1571774689254 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
StartServer("B");
}

for (int i = 0; i < 2 ; i++) {
StartServer("A");
DecreaseTraffic("A");

if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
StartServer("C");
}


}


}
}
