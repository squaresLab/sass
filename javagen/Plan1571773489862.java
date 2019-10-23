public class Plan1571773489862 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
StartServer("B");

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


} else {
StartServer("B");
}

StartServer("A");

}

}
}
