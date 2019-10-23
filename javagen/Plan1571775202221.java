public class Plan1571775202221 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
for (int i = 0; i < 3 ; i++) {
StartServer("C");
if ( DecreaseTraffic("A") ) {
StartServer("A");
} else {
StartServer("A");
}


for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


}


}
}
