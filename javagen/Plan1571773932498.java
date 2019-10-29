public class Plan1571773932498 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
IncreaseTraffic("B");
}

}

for (int i = 0; i < 4 ; i++) {
StartServer("A");
StartServer("B");
StartServer("C");


}


}
}
