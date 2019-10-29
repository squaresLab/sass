public class Plan1571774651440 extends Plan { 
public static void main(String[] args) { 
StartServer("A");
StartServer("B");
for (int i = 0; i < 4 ; i++) {
StartServer("C");
}

for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
StartServer("B");
} else {
IncreaseTraffic("B");
}

}



for (int i = 0; i < 4 ; i++) {
if ( StartServer("A") ) {
StartServer("B");
} else {
DecreaseDimmer("B");
}

}



}
}
