public class Plan1571775424580 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 4 ; i++) {
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("B");
}

}

for (int i = 0; i < 2 ; i++) {
StartServer("C");
StartServer("B");

}


for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
StartServer("C");
}

}


}
}
