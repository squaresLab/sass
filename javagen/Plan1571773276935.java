public class Plan1571773276935 extends Plan { 
public static void main(String[] args) { 
StartServer("C");
for (int i = 0; i < 4 ; i++) {
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("A");
}

StartServer("B");

}

for (int i = 0; i < 3 ; i++) {
DecreaseTraffic("A");
StartServer("A");

}



}
}
