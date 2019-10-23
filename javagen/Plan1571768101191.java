public class Plan1571768101191 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("B") ) {
StartServer("A");
} else {
StartServer("B");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}



}
}
