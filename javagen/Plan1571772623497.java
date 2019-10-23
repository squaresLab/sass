public class Plan1571772623497 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

StartServer("B");

for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
StartServer("A");
} else {
StartServer("C");
}

}

StartServer("A");

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}



}
}
