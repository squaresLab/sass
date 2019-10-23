public class Plan1571773804493 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
StartServer("A");
}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
StartServer("C");

StartServer("B");
StartServer("C");


} else {
StartServer("A");
}

}


}
}
