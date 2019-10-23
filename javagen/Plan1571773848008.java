public class Plan1571773848008 extends Plan { 
public static void main(String[] args) { 
StartServer("B");
DecreaseTraffic("A");

for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

StartServer("B");

}

StartServer("A");

StartServer("C");

if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("C");
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
StartServer("B");
}


}

} else {
StartServer("A");
}



}
}
