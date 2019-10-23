public class Plan1571770474706 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("B");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("B");
}

for (int i = 0; i < 5 ; i++) {
StartServer("A");
StartServer("C");

}



}
}
