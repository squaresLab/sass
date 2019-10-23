public class Plan1571767682173 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
StartServer("C");
}

StartServer("B");

for (int i = 0; i < 3 ; i++) {
if ( DecreaseDimmer("C") ) {
StartServer("B");
} else {
StartServer("A");
}

}

StartServer("B");

StartServer("C");

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
}



StartServer("B");

StartServer("B");
StartServer("B");


}
}
