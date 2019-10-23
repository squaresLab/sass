public class Plan1571770247808 extends Plan { 
public static void main(String[] args) { 
if ( DecreaseDimmer("B") ) {
DecreaseDimmer("C");
} else {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
DecreaseTraffic("A");

}

}

StartServer("B");

for (int i = 0; i < 5 ; i++) {
StartServer("A");
}

for (int i = 0; i < 4 ; i++) {
StartServer("C");
}



}
}
